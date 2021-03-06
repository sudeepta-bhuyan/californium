package org.eclipse.californium.core.network;

import org.eclipse.californium.core.coap.EmptyMessage;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.elements.CorrelationContext;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Matcher is the component that matches incoming messages to Exchanges. A {@link UdpMatcher} (used by Coap stack
 * running over UDP or DTLS connector) will support matching ACKs and RSTs based on MID. {@link TcpMatcher} (used by
 * Coap stack running over TCP or TLS connector).
 */
public interface Matcher {

	/**
	 * Starts this matcher.
	 */
	void start();

	/**
	 * Stops this matcher.
	 */
	void stop();

	/**
	 * Sets executor over which this matcher will perform match and clean up tasks.
	 */
	void setExecutor(ScheduledExecutorService executor);

	/**
	 * Notified when Coap stack is sending a request. Signal for matcher to begin tracking.
	 */
	void sendRequest(Exchange exchange, Request request);

	/**
	 * Notified when Coap stack is sending a response. Signal for matcher to begin tracking.
	 */
	void sendResponse(Exchange exchange, Response response);

	/**
	 * Notified when Coap stack is sending ACK or RST. Signal for matcher to begin tracking.
	 */
	void sendEmptyMessage(Exchange exchange, EmptyMessage message);

	/**
	 * Notified when Coap stack is receiving a request. Matcher is expecting to match to Exchange.
	 */
	Exchange receiveRequest(Request request);

	/**
	 * Notified when Coap stack is receiving a response. Matcher is expecting to match to Exchange.
	 */
	Exchange receiveResponse(Response response, CorrelationContext responseContext);

	/**
	 * Notified when Coap stack is receiving an ACK or RST. Matcher is expecting to match to Exchange.
	 */
	Exchange receiveEmptyMessage(EmptyMessage message);

	/**
	 * Clears internal state.
	 */
	void clear();
}
