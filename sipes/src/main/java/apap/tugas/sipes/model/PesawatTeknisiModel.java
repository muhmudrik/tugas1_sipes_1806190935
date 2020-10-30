package apap.tugas.sipes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pesawat_teknisi")
public class PesawatTeknisiModel implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pesawat", referencedColumnName = "id", nullable = false)
    private PesawatModel pesawatModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_teknisi", referencedColumnName = "id", nullable = false)
    private TeknisiModel teknisiModel;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PesawatModel getPesawatModel() {
        return this.pesawatModel;
    }

    public void setPesawatModel(PesawatModel pesawatModel) {
        this.pesawatModel = pesawatModel;
    }

    public TeknisiModel getTeknisiModel() {
        return this.teknisiModel;
    }

    public void setTeknisiModel(TeknisiModel teknisiModel) {
        this.teknisiModel = teknisiModel;
    }
}
