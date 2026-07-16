import { useContext } from "react"
import { AuthContext } from "../auth/AuthContext"

export const HomePage = () => {
  const { currentUser } = useContext(AuthContext)
  console.log(currentUser);
  return(
    <>
      <div>
        Welcome to Dineflow
      </div>
    </>
  )
}