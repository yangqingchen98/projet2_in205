package com.ensta.model;

public enum Abonnement {
	B("BASIC", 1), P( "PREMIUM", 2), V("VIP", 3);
	
	
	public String label;
    private int index;
    
    Abonnement(String label, int index) {  
        this.label = label;  
        this.index = index;  
    } 
    
    public String getLabel() {
    	return this.label;
    }

    public int getValue() {
    	return this.index;
    }
    
    public int getNombreMaxEmprunt() {
    	if (this.index == 1) return 2;
    	else if (this.index == 2) return 5;
    	else if (this.index == 3) return 20;
    	else return -1;
    }
    
    public static Abonnement StringToAbonnement(String string) {
    	switch (string) {
    	case "BASIC" : return B;
    	case "PREMIUM" : return P;
    	case "VIP" : return V;
    	default : return B;
    	}
    }
}