import { history } from "../_helpers";
import moment from "moment";

export const common = {
  checkAuth(props, type) {
    let token = localStorage.getItem("userToken");
    if (type === "front") {
      if (token) {
        props.history.push("/lead");
      }
    } else if (type === "admin") {
      if (!token) {
        props.history.push("/");
      }
    }
  },
  /**
   * Format date
   */
  formatDate(date, format) {
    if (!format) format = "D MMM - h:mm a";
    let formatedDate = moment
      .utc(date)
      .local()
      .format(format);
    return formatedDate;
  },

  /**
   * Check the response is valid or not
   */
  checkValidJSONReponse(response) {
    if (response.status === 403) {
      localStorage.removeItem("userToken");
        history.push("/");
    }
    return response.json();
  }
};
