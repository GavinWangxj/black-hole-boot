package cn.cincout.tech.springdatajpa.domain.ex;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by zhaoyu on 17-9-27.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_exception_message")
public class ExceptionMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exceptionMsg;

    public ExceptionMessage(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public ExceptionMessage(Long id, String exceptionMsg) {
        this.id = id;
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionMessage that = (ExceptionMessage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(exceptionMsg, that.exceptionMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, exceptionMsg);
    }
}
