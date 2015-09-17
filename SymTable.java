///////////////////////////////////////////////////////////////////////////////
//                   
// Main Class File:  P1.java
// File:             SymTable.java
// Semester:         cs536 Fall 2015
//
// Author:           tdvoss@wisc.edu
// CS Login:         voss
// Lecturer's Name:  Aws Albarghouthi
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.*;
import java.util.*;

public class SymTable
{
    /* List of the HashMaps holding symbols. Each HashMap represents a 
    different scope */
    private LinkedList<HashMap<String,Sym>> table;
    
    /**
     * Constructor
     *
     */
    public SymTable()
    {
        this.table = new LinkedList<HashMap<String,Sym>>();
        HashMap<String,Sym> newMap = new HashMap<String,Sym>();
        this.table.addFirst(newMap);
    }
    
    /**
     * Adds a declaration of a symbol to the HashMap of the current scope.
     * Specifically, the symbol is added the the first HashMap in the table.
     * Throws an EmptySymTableException if the table has no HashMaps. Throws 
     * the Duplicate SymException if the symbol already exists in the HashMap.
     *
     * @param name Name of the symbol declared
     * @param sym The symbol declared
     */
    public void addDecl(String name, Sym sym) throws DuplicateSymException, 
            EmptySymTableException
    {
        if (this.table.size() == 0)
        {
            throw new EmptySymTableException();
        }
        if (name == null || sym == null)
        {
            throw new NullPointerException();
        }
        if (table.get(0).containsKey(name))
        {
            throw new DuplicateSymException();
        }
        
        table.get(0).put(name, sym);
        
    }
    
    /**
     * Adds a new 'scope' by inserting a new HashMap to the front of the table 
     * list.
     *
     */
    public void addScope()
    {
        HashMap<String,Sym> newMap = new HashMap<String,Sym>();
        this.table.addFirst(newMap);
    }
    
    /**
     * Looks for a symbol with a given name in the current scope (the first
     * HashMap in the table list) and returns the Sym if found, otherwise null
     * is returned. Throws an EmptySymTableException if the table has no 
     * HashMaps.
     *
     * @param name Name of the symbol to look for
     * @return the Sym if found in the HashMap, otherwise null
     */
    public Sym lookupLocal(String name) throws EmptySymTableException
    {
        if (this.table.size() == 0)
        {
            throw new EmptySymTableException();
        }
        
        return this.table.get(0).get(name);
    }
    
    /**
     * Looks for symbol with a given name in all scopes, starting with the
     * narrowest scope (starting from beginning of table list.) If found, the
     * Sym is returned, otherwise null is returned. Throws an 
     * EmptySymTableException if the table has no HashMaps.
     *
     * @param name Name of the symbol to look for
     * @return the Sym if found in the HashMap, otherwise null
     */
    public Sym lookupGlobal(String name) throws EmptySymTableException
    {
        Sym sym = null;
        
        if (this.table.size() == 0)
        {
            throw new EmptySymTableException();
        }
        
        for (int i = 0; sym == null && i < table.size(); i++){
            sym = this.table.get(i).get(name);
        }
        
        return sym;
    }
    
    /**
     * Removes scope by removing the first HashMap in the table list. Throws an 
     * EmptySymTableException if the table has no HashMaps.
     *
     */
    public void removeScope() throws EmptySymTableException
    {
        if (this.table.size() == 0)
        {
            throw new EmptySymTableException();
        }
        
        this.table.remove(0);
    }
    
    /**
     * Prints out the contents of each HashMap in the SymTable
     *
     */
    public void print()
    {
        System.out.print("\nSym Table\n");
        for(int i = 0; i < this.table.size(); i++){
            System.out.println(this.table.get(i).toString());
        }
    }
}
