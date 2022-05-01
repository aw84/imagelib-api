package pl.aw84.imagelib.imageapi.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(
    name = "image_quality",
    typeClass = ImageQualityType.class
)
@Table(name = "storage")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "storageId")
public class Storage {
    @Id
    @Column(name = "storage_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID storageId;

    @ManyToOne()
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    private String protocol;
    private String host;

    @Column(name = "relative_path")
    private String relativePath;

    @Enumerated(EnumType.STRING)
    @Type(type="image_quality")
    private ImageQualityEnum quality;
    private String hash;

    public Storage() {
    }

    public ImageQualityEnum getQuality() {
        return quality;
    }

    public void setQuality(ImageQualityEnum quality) {
        
        this.quality = quality;
    }

    public Storage(UUID storageId, Image image, String protocol, String host, String relativePath, ImageQualityEnum quality,
            String hash) {
        this.storageId = storageId;
        this.image = image;
        this.protocol = protocol;
        this.host = host;
        this.relativePath = relativePath;
        this.quality = quality;
        this.hash = hash;
    }

    public UUID getStorageId() {
        return storageId;
    }

    public void setStorageId(UUID storageId) {
        this.storageId = storageId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Storage [hash=" + hash +
                ", quality=" + quality +
                ", host=" + host +
                ", imageId=" + image.getImageId() +
                ", protocol=" + protocol +
                ", relativePath=" + relativePath +
                ", storageId=" + storageId + "]";
    }
}
