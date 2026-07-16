import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export const OAuth2Redirect = () => {

  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const token = params.get("accessToken");
    if (token) {
      localStorage.setItem("accessToken", token);
      navigate("/");
    }
  }, []);
  return <h2>Signing you in...</h2>;
}