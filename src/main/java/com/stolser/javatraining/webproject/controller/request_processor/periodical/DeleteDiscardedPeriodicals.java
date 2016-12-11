package com.stolser.javatraining.webproject.controller.request_processor.periodical;

import com.stolser.javatraining.webproject.controller.ApplicationResources;
import com.stolser.javatraining.webproject.controller.CustomRedirectException;
import com.stolser.javatraining.webproject.controller.request_processor.RequestProcessor;
import com.stolser.javatraining.webproject.controller.utils.HttpUtils;
import com.stolser.javatraining.webproject.model.service.periodical.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDiscardedPeriodicals implements RequestProcessor {

    @Override
    public String getViewName(HttpServletRequest request, HttpServletResponse response) {
        String redirectUri = ApplicationResources.PERIODICAL_LIST_HREF;

        try {
            PeriodicalService.getInstance().deleteAllDiscarded();
            response.sendRedirect(redirectUri);

            return null;

        } catch (IOException e) {
            String message = HttpUtils.getRedirectionExceptionMessage(request,
                    redirectUri);

            throw new CustomRedirectException(message, e);
        }
    }
}
