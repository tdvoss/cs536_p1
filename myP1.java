///////////////////////////////////////////////////////////////////////////////
// 
// Title:            P1.java
// Files:            SymTable.java
//                   Sym.java
//                   DuplicateSymException.java
//                   EmptySymTableException.java
// Semester:         cs536 Fall 2015
//
// Author:           Tiffany Voss
// Email:            tdvoss@wisc.edu
// CS Login:         voss
// Lecturer's Name:  Aws Albarghouthi
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.*;
import java.util.*;

/**
 * P1
 *
 * This is a class for testing SymTable.java which has the following methods:
 *       no-arg constructor   -- create a LinkedList<HashMap<String,Sym>> with
 *                               one empty HashMap<String,Sym>
 *       void addDecl(String name, Sym sym)
 *                            -- add a symbol to the first HashMap of the
 *                               SymTable list.
 *       void addScope()      -- add a new HashMap to beginning of the SymTable
 *       Sym lookupLocal(String name)
 *                            -- look for 'name' mapped in first HashMap in the
 *                               SymTable. Returns Sym associated with 'name'
 *                               if found, null otherwise.
 *       Sym lookupGlobal(String name)
 *                            -- look for 'name' mapped in all HashMaps of the
 *                               SymTable, starting at the beginning of the
 *                               list. Returns Sym associated with 'name' if
 *                               found, null otherwise.
 *       void removeScope()   -- remove first HashMap of SymTable. Throw
 *                               exception if SymTable is empty.
 *       void print()         -- print the contents of each HashMap in SymTable
 *
 * This code tests every SymTable operation, including both correct and
 * bad calls to the operation that can throw an exception.
 * It produces output ONLY if a test fails.
 */
public class P1{
    public static void main(String[] args){
        SymTable table = new SymTable();
        
        // Test calls on an empty table for methods: addDecl, lookupLocal,
        // lookupGlobal, and removeScope
        try{
            table.removeScope();
        } catch (EmptySymTableException e){
            System.out.println("Couldn't remove scope on non-null list.");
        }
        
        Sym sym = new Sym("sym");
        try{
            table.addDecl(sym.getType(), sym);
            table.addDecl(null, null);
            System.out.println("addDecl didn't" + 
                " catch EmptySymTableException or NullPointerException\n");
        } catch (EmptySymTableException e){
            ;
        } catch (DuplicateSymException e){
            System.out.println("DuplicateSymException shouldn't be thrown\n");
        } catch (NullPointerException e){
            ;
        }
        
        try{
            table.lookupLocal("sym");
            System.out.println("lookupLocal didn't" + 
                "catch EmptySymTableException\n");
        } catch (EmptySymTableException e){
            ;
        }
        
        try{
            table.lookupGlobal("sym");
            System.out.println("lookupGlobal didn't" + 
                "catch EmptySymTableException\n");
        } catch (EmptySymTableException e){
            ;
        }
        
        try{
            table.removeScope();
            System.out.println("removeScope didn't" + 
                "catch EmptySymTableException");
        } catch (EmptySymTableException e){
            ;
        }
        
        // Add scope and a symbol
        try{
            table.addScope();
            table.addDecl(sym.getType(),sym);
        } catch (EmptySymTableException e){
            System.out.println("Couldn't addDecl to new scope.");
        } catch (DuplicateSymException e){
            System.out.println("Couldn't addDecl to new scope.");
        }
        
        // Test adding duplicate symbol
        try{
            table.addDecl(sym.getType(),sym);
            System.out.println("addDecl didn't catch DuplicateSymException");
        } catch (DuplicateSymException e){
            ;
        } catch (EmptySymTableException e){
            ;
        }
        
        // Test lookupLocal and lookupGlobal
        Sym returnVal;
        try{
            // Should find sym locally
            returnVal = table.lookupLocal("sym");
            if (!returnVal.equals(sym)){
                System.out.println("Didn't find 'sym' in local scope.");
            }
            
            // Shouldn't find sym locally
            table.addScope();
            returnVal = table.lookupLocal("sym");
            if (returnVal != null){
                System.out.println("Shouldn't have found 'sym' locally.");
            }
            
            // Should find sym globally
            returnVal = table.lookupGlobal("sym");
            if (!returnVal.equals(sym)){
                System.out.println("Didn't find 'sym' in global scope.");
            }
            
            // Should return sym from first HashMap
            Sym newSym = new Sym("sym");
            table.addDecl(newSym.getType(), newSym);
            returnVal = table.lookupGlobal("sym");
            if (!returnVal.equals(newSym)){
                System.out.println("Didn't find most local instance of 'sym'");
            }
            
        } catch (EmptySymTableException e){
            System.out.println("lookupLocal and lookupGlobal tests caused " +
                "an EmptySymTableException.");
        } catch (DuplicateSymException e){
            System.out.println("lookupLocal and lookupGlobal tests caused " +
                "a DuplicateSymException.");
        }
        
        // Test removeScope
        try{
            // Add new sym with unique name
            Sym moreSym = new Sym("more");
            table.addDecl(moreSym.getType(), moreSym);
            returnVal = table.lookupGlobal("more");
            if (!returnVal.equals(moreSym)){
                System.out.println("Didn't find 'moreSym'");
            }
            // Remove scope and shouldn't find new sym anymore
            table.removeScope();
            returnVal = table.lookupGlobal("more");
            if (returnVal != null){
                System.out.println("Shouldn't find sym from removed scope.");
            }
        } catch (EmptySymTableException e){
            System.out.println("removeScope tests caused " +
                "an EmptySymTableException.");
        } catch (DuplicateSymException e){
            System.out.println("removeScope tests caused " +
                "a DuplicateSymException.");
        }
        
    }
}
