package com.luckwine.parent.filter;

import com.luckwine.parent.violation.DubboConstraintViolation;
import org.apache.dubbo.common.constants.FilterConstants;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.validation.Validation;
import org.apache.dubbo.validation.Validator;

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
                && ConfigUtils.isNotEmpty(invoker.getUrl().getMethodParameter(invocation.getMethodName(), FilterConstants.VALIDATION_KEY))) {
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
                        return new AppResponse(e);
                    } else {
                        if (set == null) set = new HashSet<>();
                        set.add(new DubboConstraintViolation(v));
                    }
                }
                return new AppResponse(new ConstraintViolationException(e.getMessage(), set));
            } catch (RpcException e) {
                throw e;
            } catch (Throwable t) {
                return new AppResponse(t);
            }
        }
        return invoker.invoke(invocation);
    }

}
