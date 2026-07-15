import { useContext } from "react"
import { AuthContext } from "../auth/AuthContext"

export const ContactPage = () => {
  const { currentUser } = useContext(AuthContext)
  console.log(currentUser);
  return(
    <>
      <div>
        <h1>Contact Page</h1>
        <p>{currentUser.name}</p>
        <p>{currentUser.email}</p>
        <p>{currentUser.phoneNumber}</p>
      </div>
    </>
  )
}