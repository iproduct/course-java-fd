package invoicing.model;

import java.util.Date;
import java.util.Objects;

public abstract class AbstractEntity<K extends Comparable<K>, V extends Identifiable<K>>
        implements Identifiable<K>, Comparable<V> {
    private K id;
    private Date created = new Date();
    private Date updated = new Date();
    private Long createdById;
    private Long updatedById;

    public AbstractEntity() {
    }

    public AbstractEntity(K id) {
        this.id = id;
    }

    public AbstractEntity(K id, Date created, Date updated, Long createdById, Long updatedById) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.createdById = createdById;
        this.updatedById = updatedById;
    }

    @Override
    public K getId() {
        return id;
    }

    @Override
    public void setId(K id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity<?, ?> that = (AbstractEntity<?, ?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", createdById=").append(createdById);
        sb.append(", updatedById=").append(updatedById);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(V other) {
        return getId().compareTo(other.getId());
    }
}
