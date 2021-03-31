package webshop.Model;

public class FeedbackToFrontend {
        private boolean successful;

        public FeedbackToFrontend(boolean successful) {
            this.successful=successful;
        }

        public boolean isSuccessful() {
            return successful;
        }

        public void setSuccessful(boolean successful) {
            this.successful = successful;
        }
    }



