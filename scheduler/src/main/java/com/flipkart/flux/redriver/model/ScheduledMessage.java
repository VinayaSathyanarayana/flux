/*
 * Copyright 2012-2016, the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.flux.redriver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <code>ScheduledMessage</code> is a message that will be stored in DB for redriver purposes.
 *
 * @author yogesh.nachnani
 */
@Entity
@Table(name = "ScheduledMessages")
@IdClass(ScheduledMessage.ScheduledMessagePK.class)
public class ScheduledMessage implements Serializable {

    @Id
    private Long taskId;
    @Id
    private String stateMachineId;

    private long scheduledTime;

    /* For Hibernate */
    ScheduledMessage() {
    }

    public ScheduledMessage(Long taskId, String stateMachineId, Long scheduledTime) {
        this();
        this.taskId = taskId;
        this.stateMachineId = stateMachineId;
        this.scheduledTime = scheduledTime;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }

    public String getStateMachineId() {
        return stateMachineId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduledMessage)) return false;

        ScheduledMessage that = (ScheduledMessage) o;

        if (getScheduledTime() != that.getScheduledTime()) return false;
        if (!getTaskId().equals(that.getTaskId())) return false;
        return getStateMachineId().equals(that.getStateMachineId());

    }

    @Override
    public int hashCode() {
        int result = getTaskId().hashCode();
        result = 31 * result + getStateMachineId().hashCode();
        result = 31 * result + (int) (getScheduledTime() ^ (getScheduledTime() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ScheduledMessage{" +
                "taskId=" + taskId +
                ", stateMachineId='" + stateMachineId + '\'' +
                ", scheduledTime=" + scheduledTime +
                '}';
    }

    public Long getTaskId() {
        return taskId;
    }

    /**
     * <code>ScheduledMessagePK</code> is the composite primary key of "ScheduledMessages" table in DB.
     */
    static class ScheduledMessagePK implements Serializable {

        private Long taskId;
        private String stateMachineId;


        /**
         * for Hibernate
         */
        public ScheduledMessagePK() {
        }

        public ScheduledMessagePK(Long taskId, String stateMachineId) {
            this.taskId = taskId;
            this.stateMachineId = stateMachineId;
        }

        public Long getTaskId() {
            return taskId;
        }

        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }

        public String getStateMachineId() {
            return stateMachineId;
        }

        public void setStateMachineId(String stateMachineId) {
            this.stateMachineId = stateMachineId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ScheduledMessagePK)) return false;

            ScheduledMessagePK that = (ScheduledMessagePK) o;

            if (!getTaskId().equals(that.getTaskId())) return false;
            return getStateMachineId().equals(that.getStateMachineId());

        }

        @Override
        public int hashCode() {
            int result = getTaskId().hashCode();
            result = 31 * result + getStateMachineId().hashCode();
            return result;
        }
    }
}
