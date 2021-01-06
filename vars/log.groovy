def info(message) {
  echo "INFO: ${message}"
}

def warning(message) {
  MESSAGE = message.toLowerCase().capitalize()
  echo "WARNING: ${MESSAGE}"
}
