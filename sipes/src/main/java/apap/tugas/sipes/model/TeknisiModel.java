package apap.tugas.sipes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "teknisi")
public class TeknisiModel implements Serializable{
    @Id
    @Range(max = 20)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama")
    private String nama;

    @NotNull
    @Column(name = "nomor_telepon")
    private Long nomor_telepon;

    @OneToMany(mappedBy = "pesawatModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> ListPesawatTeknisi;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNomor_telepon() {
        return this.nomor_telepon;
    }

    public void setNomor_telepon(Long nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return this.ListPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> ListPesawatTeknisi) {
        this.ListPesawatTeknisi = ListPesawatTeknisi;
    }
}
