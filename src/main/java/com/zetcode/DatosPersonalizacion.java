package com.zetcode;

public class DatosPersonalizacion {
    private String colorFondo;
    private String colorZSHAPE;
    private String colorSSHAPE;
    private String colorLINESHAPE;
    private String colorTSHAPE;
    private String colorSQUARESHAPE;
    private String colorLSHAPE;
    private String colorMIRROREDLSHAPE;
    private String sonido;

    public DatosPersonalizacion(String pColorFondo, String pColorZHSAPE, String pColorSSHAPE, String pColorLINESHAPE, String pColorTSHAPE, String pColorSQUARESHAPE, String pColorLSHAPE, String pColorMIRROREDLSHAPE, String pSonido) {
        colorFondo = pColorFondo;
        colorZSHAPE = pColorZHSAPE;
        colorSSHAPE = pColorSSHAPE;
        colorLINESHAPE = pColorLINESHAPE;
        colorSQUARESHAPE = pColorSQUARESHAPE;
        colorTSHAPE = pColorTSHAPE;
        colorLSHAPE = pColorLSHAPE;
        colorMIRROREDLSHAPE = pColorMIRROREDLSHAPE;
        sonido = pSonido;
    }

    public void insertarDatos(String pColorFondo, String pColorZHSAPE, String pColorSSHAPE, String pColorLINESHAPE, String pColorTSHAPE, String pColorSQUARESHAPE, String pColorLSHAPE, String pColorMIRROREDLSHAPE, String pSonido) {
        colorFondo = pColorFondo;
        colorZSHAPE = pColorZHSAPE;
        colorSSHAPE = pColorSSHAPE;
        colorLINESHAPE = pColorLINESHAPE;
        colorSQUARESHAPE = pColorSQUARESHAPE;
        colorTSHAPE = pColorTSHAPE;
        colorLSHAPE = pColorLSHAPE;
        colorMIRROREDLSHAPE = pColorMIRROREDLSHAPE;
        sonido = pSonido;
    }

    public void obtPersonalizacion(String nombre) {
        System.out.println(nombre + " tiene la siguiente personalicación: Color del fondo " + colorFondo + " ,ZSHAPE " + colorZSHAPE + " ,SSHAPE " + colorSSHAPE + " ,LINESHAPESHAPE " + colorZSHAPE + " ,SQUARESHAPE " + colorSQUARESHAPE + " ,TSHAPE " + colorTSHAPE + " LSHAPE " + colorLSHAPE + " ,MIRROREDLSHAPE " + colorMIRROREDLSHAPE + " y el sonido: " + sonido);
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public String getColorLINESHAPE() {
        return colorLINESHAPE;
    }

    public String getColorMIRROREDLSHAPE() {
        return colorMIRROREDLSHAPE;
    }

    public String getColorLSHAPE() {
        return colorLSHAPE;
    }

    public String getColorSQUARESHAPE() {
        return colorSQUARESHAPE;
    }

    public String getColorSSHAPE() {
        return colorSSHAPE;
    }

    public String getColorTSHAPE() {
        return colorTSHAPE;
    }

    public String getColorZSHAPE() {
        return colorZSHAPE;
    }

    public String getSonido() {
        return sonido;
    }

}
