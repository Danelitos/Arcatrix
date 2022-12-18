package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;
import com.zetcode.DatosPersonalizacion;
import com.zetcode.Usuario;
import org.junit.Test;

public class DatosPersonalizacionTest {
    private DatosPersonalizacion personalizacion1=new DatosPersonalizacion("Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Classic Color","Sonido");
    private DatosPersonalizacion personalizacion2=new DatosPersonalizacion("Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Hola");

    @Test
    public void insertarDatos() {
        personalizacion1.insertarDatos("Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Rojo","Hola");
        assertEquals(personalizacion1.getColorFondo(),personalizacion2.getColorFondo());
        assertEquals(personalizacion1.getColorZSHAPE(),personalizacion2.getColorZSHAPE());
        assertEquals(personalizacion1.getColorSSHAPE(),personalizacion2.getColorSSHAPE());
        assertEquals(personalizacion1.getColorLINESHAPE(),personalizacion2.getColorLINESHAPE());
        assertEquals(personalizacion1.getColorSQUARESHAPE(),personalizacion2.getColorSQUARESHAPE());
        assertEquals(personalizacion1.getColorTSHAPE(),personalizacion2.getColorTSHAPE());
        assertEquals(personalizacion1.getColorLSHAPE(),personalizacion2.getColorLSHAPE());
        assertEquals(personalizacion1.getColorMIRROREDLSHAPE(),personalizacion2.getColorMIRROREDLSHAPE());
        assertEquals(personalizacion1.getSonido(),personalizacion2.getSonido());
    }

}
