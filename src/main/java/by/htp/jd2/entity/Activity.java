package by.htp.jd2.entity;

import java.util.Objects;

public class Activity extends AbstractEntity{

    private String title;

    public Activity() {
    }

    public Activity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        if (!super.equals(o)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(title, activity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }
}
