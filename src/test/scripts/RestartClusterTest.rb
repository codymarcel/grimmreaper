class RestartClusterTest
    fName = "dev2_hosts.txt"
    File.open(fName, "r") do |file_handle|
        file_handle.each do |host|
            curl "http://" + host + ".ops.salesforce.com:9997/cluster?command=stop" 
        end
        puts "Stopped"
        sleep(20)

        puts "Starting..."
        file_handle.each do |host|
            curl "http://" + host + ".ops.salesforce.com:9997/cluster?command=start" 
        end
    end
    
end
