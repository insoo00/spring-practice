package hello.servletjsp.web.frontcontroller.v3;

import hello.servletjsp.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
