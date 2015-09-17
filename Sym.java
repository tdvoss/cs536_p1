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

public class Sym{
    
    private String type;
    
    public Sym(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public String toString()
    {
        return this.type;
    }
}
