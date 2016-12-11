package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.model.CharSequence;

/**
 * A part of the Command pattern for classes that carry out different operation on CharSequences.
 */
public interface TaskExecutor {
    CharSequence execute();
}
