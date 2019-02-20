package com.luckwine.parent.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.validation.Validation;
import com.alibaba.dubbo.validation.Validator;
import com.luckwine.parent.violation.DubboConstraintViolation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

/**
 * 修复ConstraintDescriptorImpl没有无参构造 导致反序列化问题
 */
public class DubboValidationFilter implements Filter {

    private Validation validation;

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (validation != null && !invocation.getMethodName().startsWith("$")
                && ConfigUtils.isNotEmpty(invoker.getUrl().getMethodParameter(invocation.getMethodName(), Constants.VALIDATION_KEY))) {
            try {
                Validator validator = validation.getValidator(invoker.getUrl());
                if (validator != null) {
                    validator.validate(invocation.getMethodName(), invocation.getParameterTypes(), invocation.getArguments());
                }
            } catch (ConstraintViolationException e) {
                Set<ConstraintViolation<?>> set = null;
                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
                for (ConstraintViolation<?> v : constraintViolations) {
                    if (!v.getClass().getName().equals("org.hibernate.validator.internal.engine.ConstraintViolationImpl")) {
                        return new RpcResult(e);
                    } else {
                        if (set == null) set = new HashSet<>();
                        set.add(new DubboConstraintViolation(v));
                    }
                }
                return new RpcResult(new ConstraintViolationException(e.getMessage(), set));
            } catch (RpcException e) {
                throw e;
            } catch (Throwable t) {
                return new RpcResult(t);
            }
        }
        return invoker.invoke(invocation);
    }

}
