import { Outlet, useNavigation } from "react-router-dom"
import { Footer } from "./Footer"
import { Header } from "./Header"
import { Loader } from "./Loader";

export const AppLayout = () => {
	const navigation = useNavigation();
	// console.log(navigation);
	if(navigation.state === 'loading'){
			return <Loader/>
	}
	return(
		<>
			{/* <Header/> */}
			<Outlet/>
			{/* <Footer/> */}
		</>
	)
}