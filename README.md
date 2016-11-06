# Autonomous-Warehouse

The Warehouse

The warehouse is defined by a grid. Virtual stock items are located at particular junctions on the grid. To pick a particular item a robot must stop on the correct junction for that item and request, via a prompt on its screen, the number of that item it requires. A human will then press a button on the robot the number of times requested to indicate how many items have been given to the robot. Once items have been picked they must be dropped at one of the designated drop junctions so they can be packed for shipping. A drop also needs to be confirmed via a button press on the robot. We assume that every robot has a weight limit of 50 and the availability of stock is unlimited. The warehouse will be given a list of jobs. Each job contains a list of items plus a count for each item. Each item will be worth a certain amount of reward. Jobs can be cancelled by customers up until the point the final item from the job is dropped. All reward from partially picked but cancelled jobs is lost.

