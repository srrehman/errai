/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.errai.demo.grocery.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.client.widget.LocaleSelector;
import org.jboss.errai.ui.client.widget.OrderedList;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.Locale;
import org.jboss.errai.ui.shared.api.annotations.*;

import javax.inject.Inject;
import java.util.ArrayList;

@Templated
public class NavBar extends Composite {

  @Inject @DataField Anchor home;
  @Inject @DataField Anchor items;
  @Inject @DataField Anchor stores;

  /**
   * Could have used the {@link org.jboss.errai.ui.client.widget.LocaleListBox} here instead, but
   * you can also create your own customised component with the LocaleSelector
   */
  @Inject
  private LocaleSelector selector;
  @Inject @DataField @OrderedList
  ListWidget<Locale, LanguageItem> language;

  @Inject TransitionTo<WelcomePage> homeTab;
  @Inject TransitionTo<StoresPage> storesTab;
  @Inject TransitionTo<ItemListPage> itemsTab;

  @AfterInitialization
  public void buildLangaugeList() {
    language.setItems(new ArrayList<Locale>(selector.getSupportedLocales()));
  }

  @EventHandler("home")
  public void onHomeButtonClick(ClickEvent e) {
    homeTab.go();
  }

  @EventHandler("items")
  public void onItemsButtonClick(ClickEvent e) {
    itemsTab.go();
  }

  @EventHandler("stores")
  public void onStoresButtonClick(ClickEvent e) {
    storesTab.go();
  }

}
