package com.amur.fileMixer.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {
    private final boolean isSucceed;
    private final List<String> messages;

    public ValidationResult(boolean isSucceed) {
        this.isSucceed = isSucceed;
        this.messages = new ArrayList<>();
    }

    public ValidationResult(boolean isSucceed, String message) {
        this.isSucceed = isSucceed;
        this.messages = new ArrayList<>(Collections.singletonList(message));
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
