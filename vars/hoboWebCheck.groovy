#!/usr/bin/env groovy

def call(String url, String phrase) {
  echo "Going to check URL $url and look for the phrase $phrase"

  def data
  try {
    data = new URL(url).getText()
  } catch (Exception e) {}
  println "We got data: $data"
  if (data == null || !data.contains("Cool Jenkins X demo")) {
    println "Hoboville calls for aid! Something is awry!"
    return false
  } else {
    println "All is well in Hoboville!"
    return true
  }
}
