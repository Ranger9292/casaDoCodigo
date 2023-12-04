package br.com.casadocodigo.loja.conf;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(
	name="java:/jms/topics/CarrinhoCoprasTopico",
	interfaceName="javax/jms/Topic",
	destinationName="java:/jms/topic/CarrinhoComprasTopico"
	)
@Singleton
public class ConfigureJMSDestination {

}
