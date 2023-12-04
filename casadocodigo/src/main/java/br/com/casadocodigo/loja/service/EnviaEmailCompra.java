package br.com.casadocodigo.loja.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.casadocodigo.loja.beans.CompraDao;
import br.com.casadocodigo.loja.infra.MailSender;
import br.com.casadocodigo.loja.models.Compra;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName="destinationLookup",
				propertyValue="java:/jms/topics/CarrinhoComprasTopico"),
		@ActivationConfigProperty(
				propertyName="destinationType",
				propertyValue="javax.jms.Topic")
})
@Singleton
public class EnviaEmailCompra implements MessageListener {
	
	@Inject
	private CompraDao compraDao;
	
	@Inject
	private MailSender mailSender;
	
	public void onMessage(Message message) {
		
		try {
		TextMessage textMessage = (TextMessage) message;
		
		Compra compra;
		
			compra = compraDao.buscaPorUuid(textMessage.getText());
		
		String messageBody = "Sua compra foi realizada com sucesso, com o número de pedido " + compra.getUuid();

		mailSender.send("compras@casacodigo.com.br", compra.getUsuario().getEmail(), "Nova Compra na CDC",
				messageBody);
		
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


}
