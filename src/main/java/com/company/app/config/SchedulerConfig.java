package com.company.app.config;

import com.company.app.entity.Lot;
import com.company.app.service.application.main.api.ExchangeExtractorService;
import com.company.app.service.application.main.api.NotificationService;
import com.company.app.service.wildberries.WildberriesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ExchangeExtractorService exchangeExtractorService;
	@Autowired
	private WildberriesFacade wildberriesFacade;

	@Scheduled(fixedDelayString = "${writeExchange.timeout}")
	public void writeExchange() {
		notificationService.eventNotification(exchangeExtractorService.extractCurse());
	}

	@Scheduled(fixedDelayString = "${wildberries.timeout}")
	public void searchWildberriesLots() {
		List<Lot> desiredLots = wildberriesFacade.getDesiredLots();
		if (!CollectionUtils.isEmpty(desiredLots)) {
			notificationService.eventNotification(desiredLots);
		}
	}
}
