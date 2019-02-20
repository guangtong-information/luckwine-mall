package com.luckwine.parent.tools.sequence.exception;


public class SequenceException extends RuntimeException {
    public SequenceException() {
        super("生产序列号出错!");
    }

    public SequenceException(String message) {
        super(message);
    }
}
