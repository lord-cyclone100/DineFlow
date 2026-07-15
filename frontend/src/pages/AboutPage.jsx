import { useContext } from "react"
import { AuthContext } from "../auth/AuthContext"

export const AboutPage = () => {
  const { currentUser } = useContext(AuthContext)
  console.log(currentUser);
  return(
    <>
      <div>
        <h1>About Page</h1>
        <p>{currentUser.name}</p>
      </div>
    </>
  )
}