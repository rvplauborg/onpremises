package com.structurizr.onpremises.web.workspace;

import com.structurizr.onpremises.component.workspace.WorkspaceMetaData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelController extends AbstractWorkspaceController {

    private static final String VIEW = "model";

    @RequestMapping(value = "/share/{workspaceId}/explore/model", method = RequestMethod.GET)
    public String showPublicModel(
            @PathVariable("workspaceId") long workspaceId,
            @RequestParam(required = false) String version,
            ModelMap model
    ) {

        return showPublicView(VIEW, workspaceId, version, model, true);
    }

    @RequestMapping(value = "/share/{workspaceId}/{token}/explore/model", method = RequestMethod.GET)
    public String showSharedModel(
            @PathVariable("workspaceId") long workspaceId,
            @RequestParam(required = false) String version,
            @PathVariable("token") String token,
            ModelMap model
    ) {

        return showSharedView(VIEW, workspaceId, token, version, model, true);
    }

    @RequestMapping(value = "/workspace/{workspaceId}/explore/model", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String showAuthenticatedModel(
            @PathVariable("workspaceId") long workspaceId,
            @RequestParam(required = false) String version,
            ModelMap model
    ) {
        WorkspaceMetaData workspaceMetaData = workspaceComponent.getWorkspaceMetaData(workspaceId);
        if (workspaceMetaData == null) {
            return show404Page(model);
        }

        return showAuthenticatedView(VIEW, workspaceMetaData, version, model, true, false);
    }

}