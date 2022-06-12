import { createRouter, createWebHistory } from "vue-router";

import BoatsStartPage from "../views/startPage/BoatsStartPage";
import CottagesStartPage from "../views/startPage/CottagesStartPage";
import AdventuresStartPage from "../views/startPage/AdventuresStartPage";
import ClientAllCottages from "../views/client/entities/ClientAllCottages";
import ClientAllBoats from "../views/client/entities/ClientAllBoats";
import ClientAllAdventures from "../views/client/entities/ClientAllAdventures";
import ScheduledReservation from "../views/client/reservations/ScheduledReservation";
import ReservationHistory from "../views/client/reservations/ReservationHistory";
import HomePageInProfil from "../views/cottageOwner/HomePageInProfil";
import Reservations from "../views/cottageOwner/Reservations";
import LastReservations from "../views/cottageOwner/LastReservations";
import NextReservations from "../views/cottageOwner/FutureReservations";
import Actions from "../views/cottageOwner/Actions";
import MyCottages from "../views/cottageOwner/MyCottages";
import MyProfile from "../views/cottageOwner/MyProfile";
import Statistic from "../views/cottageOwner/Statistic";
import EditProfile from "../views/cottageOwner/EditProfile";
import NewCottage from "../views/cottageOwner/NewCottage";
import NewBoat from "../views/boatOwner/NewBoat";
import EditCottage from "../views/cottageOwner/EditCottage";
import AllRegistrationRequests from "../views/administrator/AllRegistrationRequests";
import MyService from "../views/fishingInstructor/MyService";
import AdventurePage from "../views/adventure/AdventurePage";
import FishingInstructorProfile from "../views/fishingInstructor/FishingInstructorProfile";
import ChangePasswordFishingInstructor from "../views/fishingInstructor/ChangePasswordFishingInstructor";
import AvailablePeriod from "../views/fishingInstructor/AvailablePeriod";
import CottageProfile from "../views/cottageOwner/CottageProfile";
import ClientSubscription from "../views/client/subscription/ClientSubscription"
import AdminProfile from "../views/administrator/AdminProfile"
import DeleteAccountRequests from "../views/administrator/DeleteAccountRequests"
import Percentage from "../views/administrator/Percentage"
import IncomeRecords from "../views/administrator/IncomeRecords"
import SeeAndDeleteEntities from "../views/administrator/SeeAndDeleteEntities"
import AddAdministrator from "../views/administrator/AddAdministrator"
import ChangePassword from "../views/administrator/ChangePassword"
import Complaints from "../views/administrator/Complaints"
import MyBoats from "../views/boatOwner/MyBoats"
import CottageActions from "../views/client/reservations/CottageActions"
import BoatProfile from "../views/boatOwner/BoatProfile"
import EditBoat from "../views/boatOwner/EditBoat"
import Evaluate from "../views/administrator/Evaluate"
import BuisinessReport from "../views/administrator/BuisinessReport"
import AddAdventure from "../views/fishingInstructor/AddAdventure"
import CreateAction from "../views/fishingInstructor/CreateAction"
import EditAdventure from "../views/fishingInstructor/EditAdventure"
import MyAdventureReservations from "../views/fishingInstructor/MyAdventureReservations"
import AdventureProfile from "../views/fishingInstructor/AdventureProfile"
import AddService from "../views/fishingInstructor/AddService"
import MyReportOfReservation from "../views/fishingInstructor/MyReportOfReservation"
import Requests from "../views/administrator/Requests"
import CreateReservation from "../views/fishingInstructor/CreateReservation"
import Calendarr from "../views/fishingInstructor/Calendarr"
const routes = [
  {
    path: "/",
    name: "BoatsStartPage",
    component: BoatsStartPage,
  },
  {
    path: "/AdventureProfile",
    name: "AdventureProfile",
    component: AdventureProfile,
  },
  {
    path: "/Calendarr",
    name: "Calendarr",
    component: Calendarr,
  },
  {
    path: "/AddService",
    name: "AddService",
    component: AddService,
  },
  {
    path: "/CreateReservation",
    name: "CreateReservation",
    component: CreateReservation,
  },
  {
    path: "/MyAdventureReservations",
    name: "MyAdventureReservations",
    component: MyAdventureReservations,
  },
  
  {
    path: "/percentage",
    name: "Percentage",
    component: Percentage,
  },
  {
    path: "/EditAdventure",
    name: "EditAdventure",
    component: EditAdventure,
  },
  {
    path: "/Requests",
    name: "Requests",
    component: Requests,
  },
  {
    path: "/buisinessReport",
    name: "BuisinessReport",
    component: BuisinessReport,
  },
  {
    path: "/CreateAction",
    name: "CreateAction",
    component: CreateAction,
  },
  {
    path: "/complaints",
    name: "Complaints",
    component: Complaints,
  },
  {
    path: "/evaluate",
    name: "Evaluate",
    component: Evaluate,
  },
  {
    path: "/incomeRecords",
    name: "IncomeRecords",
    component: IncomeRecords,
  },
  {
    path: "/deleteAccountRequests",
    name: "DeleteAccountRequests",
    component: DeleteAccountRequests,
  },
  {
    path: "/cottages",
    name: "CottageStartPage",
    component: CottagesStartPage,
  },
  {
    path: "/adventures",
    name: "AdventuresStartPage",
    component: AdventuresStartPage,
  },
  
  {
    path: "/MyReportOfReservation",
    name: "MyReportOfReservation",
    component: MyReportOfReservation,
  },
  {
    path: "/client",
    name: "ClientAllCottages",
    component: ClientAllCottages,
  },
  {
    path: "/addAdministrator",
    name: "AddAdministrator",
    component: AddAdministrator,
  },
  {
    path: "/adventurePage",
    name: "AdventurePage",
    component: AdventurePage,
  },
  {
    path: "/administrator",
    name: "AllRegistrationRequests",
    component: AllRegistrationRequests,
  },
  {
    path: "/fishingInstructor",
    name: "MyService",
    component: MyService,
  },
  {
    path: "/ChangePasswordFishingInstructor",
    name: "ChangePasswordFishingInstructor",
    component: ChangePasswordFishingInstructor,
  },
  {
    path: "/AvailablePeriod",
    name: "AvailablePeriod",
    component: AvailablePeriod,
  },
  {
    path: "/AddAdventure",
    name: "AddAdventure",
    component: AddAdventure,
  },
  {
    path: "/seeAndDeleteEntities",
    name: "SeeAndDeleteEntities",
    component: SeeAndDeleteEntities,
  },
  {
    path: "/fishingInstructorProfile",
    name: "FishingInstructorProfile",
    component: FishingInstructorProfile,
  },
  {
    path: "/clientBoats",
    name: "ClientAllBoats",
    component: ClientAllBoats,
  },
  {
    path: "/clientAdventures",
    name: "ClientAllAdventures",
    component: ClientAllAdventures,
  },
  {
    path: "/clientScheduledReservations",
    name: "ScheduledReservation",
    component: ScheduledReservation,
  },
  {
    path: "/clientReservationHistory",
    name: "ReservationHistory",
    component: ReservationHistory,
  },
  {
    path: "/cottageOwnerHomePage",
    name: "HomePageInProfil",
    component: HomePageInProfil,
  },
  {
    path: "/myCottages",
    name: "MyCottages",
    component: MyCottages,
  },
  {
    path: "/myBoats",
    name: "MyBoats",
    component: MyBoats,
  },
  {
    path: "/changePassword",
    name: "ChangePassword",
    component: ChangePassword,
  },
  {
    path: "/myProfile",
    name: "MyProfile",
    component: MyProfile,
  },
  {
    path: "/adminProfile",
    name: "AdminProfile",
    component: AdminProfile,
  },
  {
    path: "/editProfile",
    name: "EditProfile",
    component: EditProfile,
  },
  {
    path: "/statistic",
    name: "Statistic",
    component: Statistic,
  },
  {
    path: "/newCottage",
    name: "NewCottage",
    component: NewCottage,
  },
  {
    path: "/newBoat",
    name: "NewBoat",
    component: NewBoat,
  },
  {
    path: "/cottageProfile/:id",
    name: "CottageProfile",
    component: CottageProfile,
  },
   {
    path: "/boatProfile/:id",
    name: "BoatProfile",
    component: BoatProfile,
  },
  {
    path: "/editCottage/:id",
    name: "EditCottage",
    component: EditCottage,
  },
  {
    path: "/editBoat/:id",
    name: "EditBoat",
    component: EditBoat,
  },
  {
    path: "/ClientSubscription",
    name: "ClientSubscription",
    component: ClientSubscription,
  },
  {
    path: "/cottageActions/:id",
    name: "CottageActions",
    component: CottageActions,
  },
  {
    path: "/reservations",
    name: "Reservations",
    component: Reservations,
  },
  {
    path: "/lastReservations",
    name: "LastReservations",
    component: LastReservations,
  },
  {
    path: "/nextReservations",
    name: "NextReservations",
    component: NextReservations,
  },
  {
    path: "/actions",
    name: "Actions",
    component: Actions,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
