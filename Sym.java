///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  P1.java
// File:             Sym.java
// Semester:         cs536 Fall 2015
//
// Author:           tdvoss@wisc.edu
// CS Login:         voss
// Lecturer's Name:  Aws Albarghouthi
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.*;
import java.util.*;

/**
 * Sym
 * 
 * This class represents a token (or symbol) within a program. Each token 
 * is initiated with the type of token it is.
 *
 * @author Tiffany Voss
 */
public class Sym{
    
    private String type;
    
    /**
     * Constructor
     *
     */
    public Sym(String type)
    {
        this.type = type;
    }
    
    /**
     * Gets the type of the Sym
     *
     * @return the string representing the Sym's type
     */
    public String getType()
    {
        return this.type;
    }
    
    /**
     * Returns a string representation of the Sym for printing purposes
     *
     * @return the string representing the Sym's type
     */
    public String toString()
    {
        return this.type;
    }
}
