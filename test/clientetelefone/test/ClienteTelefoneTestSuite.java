/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({clientetelefone.test.jUnitClienteTeste.class, clientetelefone.test.jUnitTelefoneTeste.class})
public class ClienteTelefoneTestSuite {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ClienteTelefoneTestSuite.class);
        
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        
        System.out.println(result.wasSuccessful());
    }
}
