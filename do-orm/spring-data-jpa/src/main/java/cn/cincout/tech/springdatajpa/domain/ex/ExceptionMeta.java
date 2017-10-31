package cn.cincout.tech.springdatajpa.domain.ex;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by zhaoyu on 17-9-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_exception_meta",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"applicationName", "methodName", "exceptionType"})}
)
public class ExceptionMeta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String applicationName;

    @Column(length = 200)
    private String methodName;

    @Column(length = 200)
    private String exceptionType;

    public ExceptionMeta(String applicationName, String methodName, String exceptionType) {
        this.applicationName = applicationName;
        this.methodName = methodName;
        this.exceptionType = exceptionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionMeta meta = (ExceptionMeta) o;
        return Objects.equals(applicationName, meta.applicationName) &&
                Objects.equals(methodName, meta.methodName) &&
                Objects.equals(exceptionType, meta.exceptionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationName, methodName, exceptionType);
    }

    public static ExceptionMetaBuilder builder() {
        return new ExceptionMetaBuilder();
    }

    public static class ExceptionMetaBuilder {
        private ExceptionMeta exceptionMeta;

        public ExceptionMetaBuilder() {
            this.exceptionMeta = new ExceptionMeta();
        }

        /**
         * keep application name
         *
         * @param applicationName length < 100
         * @return
         */
        public ExceptionMetaBuilder applicationName(String applicationName) {
            if (StringUtils.isNoneEmpty(applicationName) && applicationName.length() > 100) {
                this.exceptionMeta.applicationName = applicationName.substring(0, 100);
            } else if (StringUtils.isNoneEmpty(applicationName) && applicationName.length() <= 100) {
                this.exceptionMeta.applicationName = applicationName;
            }
            return this;
        }

        /**
         * keep exception type
         *
         * @param exceptionType length < 200
         * @return
         */
        public ExceptionMetaBuilder exceptionType(String exceptionType) {
            if (StringUtils.isNoneEmpty(exceptionType) && exceptionType.length() > 200) {
                this.exceptionMeta.exceptionType = exceptionType.substring(0, 200);
            } else if (StringUtils.isNoneEmpty(exceptionType) && exceptionType.length() <= 200) {
                this.exceptionMeta.exceptionType = exceptionType;
            }
            return this;
        }

        /**
         * keep exception message
         *
         * @param methodName length < 200
         * @return
         */
        public ExceptionMetaBuilder methodName(String methodName) {
            if (StringUtils.isNoneEmpty(methodName) && methodName.length() > 200) {
                this.exceptionMeta.methodName = methodName.substring(0, 200);
            } else if (StringUtils.isNoneEmpty(methodName) && methodName.length() <= 200) {
                this.exceptionMeta.methodName = methodName;
            }
            return this;
        }

        public ExceptionMeta build() {
            return this.exceptionMeta;
        }
    }

}
