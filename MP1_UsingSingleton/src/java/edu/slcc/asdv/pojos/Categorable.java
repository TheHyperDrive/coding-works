/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.pojos;

/**
 *
 * @author ASDV2
 * @param <T>
 */
public interface Categorable<T extends CharSequence>
{
    public T getCategory();
    public void setCategory( T t );
    
}
