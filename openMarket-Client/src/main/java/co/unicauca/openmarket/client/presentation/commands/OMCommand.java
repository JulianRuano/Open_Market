/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.commands;

/**
 *
 * @author brayan majin, julian ruano
 */
public abstract class OMCommand {
    public abstract void make();
    public abstract void unmake();
    public abstract  void redo();
}
