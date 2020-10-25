package apap.tugas.sipes.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable {

    @Id
    @Size(max = 20)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(max = 255)
    @Column(name = "kode_bandara_asal")
    private String kode_bandara_asal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode_bandara_tujuan")
    private String kode_bandara_tujuan;
    
    @NotNull
    @Column(name = "waktu_berangkat")
    private LocalDate waktu_berangkat;
    
    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_penerbangan", unique = true)
    private String nomor_penerbangan;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pesawat", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PesawatModel pesawatModel;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode_bandara_asal() {
        return this.kode_bandara_asal;
    }

    public void setKode_bandara_asal(String kode_bandara_asal) {
        this.kode_bandara_asal = kode_bandara_asal;
    }

    public String getKode_bandara_tujuan() {
        return this.kode_bandara_tujuan;
    }

    public void setKode_bandara_tujuan(String kode_bandara_tujuan) {
        this.kode_bandara_tujuan = kode_bandara_tujuan;
    }

    public LocalDate getWaktu_berangkat() {
        return this.waktu_berangkat;
    }

    public void setWaktu_berangkat(LocalDate waktu_berangkat) {
        this.waktu_berangkat = waktu_berangkat;
    }

    public String getNomor_penerbangan() {
        return this.nomor_penerbangan;
    }

    public void setNomor_penerbangan(String nomor_penerbangan) {
        this.nomor_penerbangan = nomor_penerbangan;
    }

    public PesawatModel getPesawatModel() {
        return this.pesawatModel;
    }

    public void setPesawatModel(PesawatModel pesawatModel) {
        this.pesawatModel = pesawatModel;
    }
}
