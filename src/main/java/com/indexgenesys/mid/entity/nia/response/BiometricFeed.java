/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indexgenesys.mid.entity.nia.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ernest
 */
@Getter
@Setter
public class BiometricFeed implements Serializable{
  public Face face;  
}
