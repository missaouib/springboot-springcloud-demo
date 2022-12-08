package com.example.disruptordemo.multi;

import com.lmax.disruptor.RingBuffer;

public class Producer {
	
	private RingBuffer<Order> ringBuffer;
	
	public Producer(RingBuffer<Order> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void sendData(String uuid) {
		long sequence = ringBuffer.next();
		try {
			Order order = ringBuffer.get(sequence);
			order.setId(uuid+"==="+sequence);
		} finally {
			try {
				ringBuffer.publish(sequence);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
