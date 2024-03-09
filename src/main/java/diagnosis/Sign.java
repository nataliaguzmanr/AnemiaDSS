package diagnosis;

import java.util.Date;
import java.util.Objects;

public class Sign {

    private final Integer id;
    private Date date;

    public Sign(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sign sign = (Sign) o;
        return Objects.equals(id, sign.id) && Objects.equals(date, sign.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
