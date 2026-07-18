import { useState } from "react"
import { SeeAllUsersSection } from "../components/ui/SeeAllUsersSection"
import { SeeAllRestaurantsSection } from "../components/ui/SeeAllRestaurantsSection"

export const AdminPanel = () => {

  const [ tab, setTab ] = useState('Users')
  
  const handleTabSwitching = (e) => {
    setTab(e.target.textContent)
  }

  return(
    <>
      Admin
      <div className="flex gap-2" onClick={handleTabSwitching}>
        <button className="border px-2">Users</button>
        <button className="border px-2">Restaurants</button>
      </div>
      {
        tab === 'Users' ? <SeeAllUsersSection /> : <SeeAllRestaurantsSection />
      }
    </>
  )
}