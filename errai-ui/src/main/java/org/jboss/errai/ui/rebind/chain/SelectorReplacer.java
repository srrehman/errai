package org.jboss.errai.ui.rebind.chain;

import org.jboss.errai.ui.rebind.less.LessStyleGenerator;
import org.jboss.errai.ui.shared.chain.Command;
import org.jboss.errai.ui.shared.chain.Context;
import org.w3c.dom.Element;

import java.util.Map;

import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.jboss.errai.ui.rebind.chain.TemplateCatalog.RESULT;

/**
 * @author edewit@redhat.com
 */
public class SelectorReplacer implements Command {

  static final String MAPPING = "mapping";

  @Override
  @SuppressWarnings("unchecked")
  public void execute(Context context) {
    Map<String, String> selectorMap = (Map<String, String>) context.get(MAPPING);
    Element element = (Element) context.get(TemplateCatalog.ELEMENT);
    String selector = element.getAttribute("class");
    if (isNotEmpty(selector)) {
      final String[] classSelectors = selector.split(" ");
      for (String classSelector : classSelectors) {
        final String obfuscatedSelector = selectorMap.get(classSelector);
        if (obfuscatedSelector != null) {
          element.setAttribute("class", selector.replaceAll(classSelector, obfuscatedSelector));
          context.put(RESULT, element.getOwnerDocument());
        }
      }
    }
  }

  @Override
  public Context createInitialContext() {
    Context context = new Context();
    context.put(SelectorReplacer.MAPPING, LessStyleGenerator.getStyleMapping());

    return context;
  }
}
