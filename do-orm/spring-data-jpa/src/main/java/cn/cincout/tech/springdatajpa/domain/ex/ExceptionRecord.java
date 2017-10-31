package cn.cincout.tech.springdatajpa.domain.ex;

import cn.cincout.tech.springdatajpa.application.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhaoyu on 17-7-25.
 *
 * @author zhaoyu
 * @date 17-7-25
 * @sine 1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "applicationName", "exceptionType", "exceptionMsg"})
@Entity
@Table(name = "t_exception_record")
public class ExceptionRecord implements Serializable {
    public static final String TX_ID_DELIMITER = "@";
    public static final int EXCEPTION_MSG_SIZE = 5;
    public static final int TX_IDS_SIZE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ex_meta_id", referencedColumnName = "id")
    private ExceptionMeta exceptionMeta;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ex_record_id", referencedColumnName = "id")
    private List<ExceptionMessage> exceptionMessageList = new ArrayList<>();

    private Integer count;
    @Column(length = 900)
    private String txIds;
    @Column(length = 20)
    private String timestamp;

    private Long startTime;

    public void setTxId(String txId) {
        if (StringUtils.isEmpty(this.txIds)) {
            this.txIds = txId;
        }
        else {
            // filter repeat tx id
            if (this.txIds.contains(txId)) {
                return ;
            }

            int size = this.txIds.split(TX_ID_DELIMITER).length;
            if (size < TX_IDS_SIZE) {
                this.txIds = this.txIds + TX_ID_DELIMITER + txId;
            }
        }
    }

    public void setExceptionMessage(ExceptionMessage exceptionMessage) {
        // filter same message
        if (this.exceptionMessageList.size() > 0 &&
                this.exceptionMessageList.contains(exceptionMessage)) {
            return ;
        }
        // control the size of exception message size
        if (this.exceptionMessageList.size() < EXCEPTION_MSG_SIZE) {
            this.exceptionMessageList.add(exceptionMessage);
        }
    }

    @JsonIgnore
    public String[] getAllTxIds() {
        return StringUtils.split(this.txIds, TX_ID_DELIMITER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionRecord that = (ExceptionRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static ExceptionRecordBuilder builder() {
        return new ExceptionRecordBuilder();
    }

    public static class ExceptionRecordBuilder {
        private ExceptionRecord exceptionRecord;

        public ExceptionRecordBuilder() {
            this.exceptionRecord = new ExceptionRecord();
        }

        /**
         * set exception related exception meta
         * @param exceptionMeta
         * @return ExceptionRecordBuilder
         */
        public ExceptionRecordBuilder exceptionMeta(ExceptionMeta exceptionMeta) {
            this.exceptionRecord.exceptionMeta = exceptionMeta;
            return this;
        }

        /**
         * exception message size <= 5
         * @param exceptionMessage
         * @return
         */
        public ExceptionRecordBuilder exceptionMessage(ExceptionMessage exceptionMessage) {
           this.exceptionRecord.setExceptionMessage(exceptionMessage);
            return this;
        }

        public ExceptionRecordBuilder count(int count) {
            this.exceptionRecord.count = count;
            return this;
        }

        /**
         * txId size <= 5
         *
         * @param txId
         * @returnb ExceptionRecordBuilder
         */
        public ExceptionRecordBuilder txIds(String txId) {
            this.exceptionRecord.setTxId(txId);
            return this;
        }

        public ExceptionRecordBuilder timestamp(long timestamp) {
            String timestampStr = DateTimeUtils.longToDateStr(timestamp, "yyyy-MM-dd HH:mm:ss");
            this.exceptionRecord.timestamp = timestampStr;
            return this;
        }

        public ExceptionRecordBuilder startTime(long startTime) {
            this.exceptionRecord.startTime = startTime;
            return this;
        }

        public ExceptionRecord build() {
            return this.exceptionRecord;
        }
    }
}
