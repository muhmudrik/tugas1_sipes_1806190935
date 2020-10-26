package apap.tugas.sipes.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable{
    @Id
    @Size(max = 20)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai")
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", unique = true)
    private String nomor_seri;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_dibuat")
    private String tempat_dibuat;

    @NotNull
    @Column(name = "tanggal_dibuat")
    private LocalDate tanggal_dibuat;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_pesawat")
    private String jenis_pesawat;

    @OneToMany(mappedBy = "pesawatModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @OneToMany(mappedBy = "pesawatModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> ListPesawatTeknisi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipeModel tipeModel;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return this.maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomor_seri() {
        return this.nomor_seri;
    }

    public void setNomor_seri(String nomor_seri) {
        this.nomor_seri = nomor_seri;
    }

    public String getTempat_dibuat() {
        return this.tempat_dibuat;
    }

    public void setTempat_dibuat(String tempat_dibuat) {
        this.tempat_dibuat = tempat_dibuat;
    }

    public LocalDate getTanggal_dibuat() {
        return this.tanggal_dibuat;
    }

    public void setTanggal_dibuat(LocalDate tanggal_dibuat) {
        this.tanggal_dibuat = tanggal_dibuat;
    }

    public String getJenis_pesawat() {
        return this.jenis_pesawat;
    }

    public void setJenis_pesawat(String jenis_pesawat) {
        this.jenis_pesawat = jenis_pesawat;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return this.listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return this.ListPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> ListPesawatTeknisi) {
        this.ListPesawatTeknisi = ListPesawatTeknisi;
    }

    public TipeModel getTipeModel() {
        return this.tipeModel;
    }

    public void setTipeModel(TipeModel tipeModel) {
        this.tipeModel = tipeModel;
    }
}
