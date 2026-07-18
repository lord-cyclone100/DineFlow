import { useEffect, useState } from "react"
import { api } from "../../api/axios"
import { AdminPanelRestaurantCard } from "./AdminPanelRestaurantCard"
import { useCreateNewRestaurantModal } from "../../store/store"
import { CreateRestaurantModal } from "./CreateRestaurantModal"

export const SeeAllRestaurantsSection = () => {

  const [restaurantList, setRestaurantList] = useState([])
  const { createNewRestaurantModalShow, setCreateNewRestaurantModalShow } = useCreateNewRestaurantModal()

  useEffect(()=>{
    const showAllRestaurants = async() => {
      try {
        const response = await api.get('/restaurants')
        setRestaurantList(response.data)
      } catch (error) {
        console.error(error)
      }
    }
    showAllRestaurants()
  },[])

  if(restaurantList.length !== 0){
    return (
      <>
        <div className="flex gap-2">
          <p>See All Restaurants</p>
          <button className="border px-2 bg-sky-700 text-white" onClick={()=>setCreateNewRestaurantModalShow(true)}>Create Restaurant</button>
        </div>
        <CreateRestaurantModal />
        {
          restaurantList.map((restaurant) => (
            <AdminPanelRestaurantCard value={restaurant} />
          ))
        }
  
      </>
    )
  }
}