package com.github.nicholas1991.urlshortener.webapi.tasks.dispatchers;

import com.github.nicholas1991.urlshortener.webapi.tasks.models.Task;

public interface TaskDispatcher {

  void dispatch(Task task);

}
