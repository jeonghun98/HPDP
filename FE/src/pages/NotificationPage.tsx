import React from 'react';
import { OptionTopbar } from "../components/common/TopBar";
import CustomizedTabs from './../components/CustomizedTabs';
import Activity from '../components/Activity';
import Message from '../components/Message';

const NotificationPage = () => {
    const tabProps = {
      '활동': <Activity />,
      '쪽지': <Message />
    }

    return (
    <div>
      <OptionTopbar text={"알림"} />
      <CustomizedTabs tabProps={tabProps} />
    </div>
  )
}

export default NotificationPage