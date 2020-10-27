package apap.tugas.sipes.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;
    
    public void addPesawat(PesawatModel pesawat){
        String noseri = "";
        noseri += pesawat.getJenis_pesawat() + pesawat.getTipeModel().getId();
        Integer taunBuat = pesawat.getTanggal_dibuat().getYear();
        noseri += new StringBuilder().append(taunBuat).reverse();
        noseri += taunBuat + 8;
        noseri += getAlphaNumericString(2);
        pesawat.setNomor_seri(noseri);
        pesawatDb.save(pesawat);
    }

    /**
     * Source GeeksForGeeks
     * @param Letter_Length
     * @return
     */
	public String getAlphaNumericString(int n) 
	{ 

		// lower limit for LowerCase Letters 
		int lowerLimit = 65; 

		// lower limit for LowerCase Letters 
		int upperLimit = 90; 

		Random random = new Random(); 

		// Create a StringBuffer to store the result 
		StringBuffer r = new StringBuffer(n); 

		for (int i = 0; i < n; i++) { 

			// take a random value between 65 and 90 
			int nextRandomChar = lowerLimit 
								+ (int)(random.nextFloat() 
										* (upperLimit - lowerLimit + 1)); 

			// append a character at the end of bs 
			r.append((char)nextRandomChar); 
		} 

		// return the resultant string 
		return r.toString(); 
	} 
}
