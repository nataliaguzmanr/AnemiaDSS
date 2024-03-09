package diagnosis;

import java.util.Objects;

public class Qualitative extends Symptom{

    private Boolean presence;

    public Qualitative(Integer id, String name, Boolean presence) {
        super(id, name);
        this.presence = presence;
    }

    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Qualitative that = (Qualitative) o;
        return Objects.equals(presence, that.presence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), presence);
    }

    @Override
    public String toString() {
        return "Qualitative{" +
                "presence=" + presence +
                '}';
    }
}
