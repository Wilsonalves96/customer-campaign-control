package com.customer.campaign.control.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.customer.campaign.control.model.Campaign;
import com.customer.campaign.control.model.Subscriber;

public class CampaignClient {
	private Campaign campaign;

	public CampaignClient(Campaign campaign) {
		this.campaign = campaign;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public void notifySubscribers() throws IOException, URISyntaxException {
		Set<Subscriber> subscribers = campaign.getSubscribers();

		for (Subscriber subscriber : subscribers) {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			HttpPost httpPost = new HttpPost(subscriber.getUrl());

			httpPost.setHeader("Content-type", "application/json");
			
			StringEntity stringEntity = new StringEntity(subscriber.getParameters(),"UTF-8");
			stringEntity.setContentType("application/json");

			httpPost.getRequestLine();
			httpPost.setEntity(stringEntity);

			httpClient.execute(httpPost);
		}
	}
}
